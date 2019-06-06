package com.hui.base.common.util;

import java.io.*;

/**
 * <b><code>LngLatTransfromUtil</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2018/12/29 10:47.
 *
 * @author Hu weihui
 */
public class LngLatTransportUtil {

    /*
     * 大地坐标系资料WGS-84 长半径a=6378137 短半径b=6356752.3142 扁率f=1/298.2572236
     */
    /**
     * 长半径a=6378137
     */
    private static double a = 6378137;
    /**
     * 短半径b=6356752.3142
     */
    private static double b = 6356752.3142;
    /**
     * 扁率f=1/298.2572236
     */
    private static double f = 1 / 298.2572236;

    /**
     * 计算另一点经纬度
     *
     * @param lon  经度
     * @param lat  维度
     * @param brng 方位角
     * @param dist 距离（米）
     */
    public static String computerThatLonLat(double lon, double lat, double brng, double dist) {

        double alpha1 = rad(brng);
        double sinAlpha1 = Math.sin(alpha1);
        double cosAlpha1 = Math.cos(alpha1);

        double tanU1 = (1 - f) * Math.tan(rad(lat));
        double cosU1 = 1 / Math.sqrt((1 + tanU1 * tanU1));
        double sinU1 = tanU1 * cosU1;
        double sigma1 = Math.atan2(tanU1, cosAlpha1);
        double sinAlpha = cosU1 * sinAlpha1;
        double cosSqAlpha = 1 - sinAlpha * sinAlpha;
        double uSq = cosSqAlpha * (a * a - b * b) / (b * b);
        double A = 1 + uSq / 16384 * (4096 + uSq * (-768 + uSq * (320 - 175 * uSq)));
        double B = uSq / 1024 * (256 + uSq * (-128 + uSq * (74 - 47 * uSq)));

        double cos2SigmaM = 0;
        double sinSigma = 0;
        double cosSigma = 0;
        double sigma = dist / (b * A), sigmaP = 2 * Math.PI;
        while (Math.abs(sigma - sigmaP) > 1e-12) {
            cos2SigmaM = Math.cos(2 * sigma1 + sigma);
            sinSigma = Math.sin(sigma);
            cosSigma = Math.cos(sigma);
            double deltaSigma = B * sinSigma * (cos2SigmaM + B / 4 * (cosSigma * (-1 + 2 * cos2SigmaM * cos2SigmaM)
                    - B / 6 * cos2SigmaM * (-3 + 4 * sinSigma * sinSigma) * (-3 + 4 * cos2SigmaM * cos2SigmaM)));
            sigmaP = sigma;
            sigma = dist / (b * A) + deltaSigma;
        }

        double tmp = sinU1 * sinSigma - cosU1 * cosSigma * cosAlpha1;
        double lat2 = Math.atan2(sinU1 * cosSigma + cosU1 * sinSigma * cosAlpha1,
                (1 - f) * Math.sqrt(sinAlpha * sinAlpha + tmp * tmp));
        double lambda = Math.atan2(sinSigma * sinAlpha1, cosU1 * cosSigma - sinU1 * sinSigma * cosAlpha1);
        double C = f / 16 * cosSqAlpha * (4 + f * (4 - 3 * cosSqAlpha));
        double L = lambda - (1 - C) * f * sinAlpha
                * (sigma + C * sinSigma * (cos2SigmaM + C * cosSigma * (-1 + 2 * cos2SigmaM * cos2SigmaM)));

        double revAz = Math.atan2(sinAlpha, -tmp); // final bearing

//        System.out.println(revAz);
//        System.out.println(lon + deg(L) + "," + deg(lat2));

        return revAz + "\t" + (lon + deg(L)) + "\t" + deg(lat2);
    }

    /**
     * 度换成弧度
     *
     * @param d 度
     * @return 弧度
     */
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 弧度换成度
     *
     * @param x 弧度
     * @return 度
     */
    private static double deg(double x) {
        return x * 180 / Math.PI;
    }


    public static void main(String[] args) throws IOException {

        FileReader file = new FileReader("C:\\Users\\hu545\\Desktop\\经纬度.txt");
        BufferedReader reader = new BufferedReader(file);

        FileWriter fileWriter = new FileWriter("C:\\Users\\hu545\\Desktop\\经纬度_RESULT.txt");
        BufferedWriter writer = new BufferedWriter(fileWriter);

        String line ;
        while ((line = reader.readLine()) != null) {
            String[] split = line.split("\t");
            String name = split[0];
            String seq = split[1];
            double lon = Double.valueOf(split[2]);
            double lat = Double.valueOf(split[3]);
            double brng = Double.valueOf(split[6]);
            double dist = 800;
            String s = LngLatTransportUtil.computerThatLonLat(lon, lat, brng, dist);
            String result = new String(name+"\t"+seq+"\t"+lon + "\t" + lat + "\t" + brng + "\t" + dist + "\t" + s +"\n");
            System.out.println(result);
            writer.write(result);
        }
        writer.close();

//        double lon = 114.1125796;
//        double lat = 22.5498711917317;
//        double brng = 58.9829860805238;
//        double dist = 800;
//        String s = LngLatTransportUtil.computerThatLonLat(lon, lat, brng, dist);
//        System.out.println(s);
    }

}
