package com.hui.base.elasticsearch;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * <b><code>ElasticSearchTest</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/6/29 14:56.
 *
 * @author HuWeihui
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ElasticSearchTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void testInsert(){
        Account account = Account.builder().id(1).account("admini").name("huweihui").pwd("123").build();
        IndexQuery indexQuery = new IndexQueryBuilder().withId(account.getId().toString()).withObject(account).build();
        elasticsearchTemplate.index(indexQuery);
    }


    @Test
    public void testQuery(){
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.queryStringQuery("admin")).build();
        List<Account> accounts = elasticsearchTemplate.queryForList(searchQuery, Account.class);
        log.info(Arrays.toString(new List[]{accounts}));
    }


}
