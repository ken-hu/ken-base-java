package com.hui.base.design.behavior.interpreter;

import java.util.Arrays;

/**
 * <b><code>InterpreterDemo</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/7/25 21:21.
 *
 * @author HuWeihui
 */
public class InterpreterDemo {

    //客户端
    public static void main(String[] args) {
        String[] infos = new String[]{"1","+","1","="};
        Context context = new Context(infos);
        context.operation();
    }

    /**
     * 抽象表达式
     */
    interface AbstractExpression{

        boolean interpreter(String[] infos);

        String result();
    }

    /**
     * 终结表达式
     */
    static class TerminalExpression implements AbstractExpression{

        @Override
        public boolean interpreter(String[] infos) {
            for (String info : infos) {
                if (info.equals("=")){
                    return true;
                }
            }
            return false;
        }

        @Override
        public String result() {
            return "";
        }
    }

    /**
     * 非终结表达式
     */
    static class NonterminalExpression implements AbstractExpression{
        private int val;

        private AbstractExpression expression;

        public NonterminalExpression(AbstractExpression expression) {
            this.expression = expression;
        }

        @Override
        public boolean interpreter(String[] infos) {
            if (expression.interpreter(infos)){
                Integer val1 = Integer.valueOf(infos[0]);
                Integer val2 = Integer.valueOf(infos[2]);

                if (infos[1].equals("+")){
                    val = val1 + val2;
                }
                return true;
            }
            return false;
        }

        @Override
        public String result() {
            return String.valueOf(val);
        }
    }

    /**
     * 上下文环境
     */
    static class Context{
        private AbstractExpression expression;

        private String[] infos;

        public Context(String[] infos) {
            this.infos = infos;

            AbstractExpression abstractExpression = new TerminalExpression();

            expression = new NonterminalExpression(abstractExpression);
        }


        public void operation(){
            boolean interpreterSuccess = expression.interpreter(infos);

            if (interpreterSuccess){
                System.out.println(Arrays.toString(infos) + " " + expression.result());
            }else {
                System.out.println();
            }
        }

    }
}
