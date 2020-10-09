package top.idwangmo.whitebird.elasticsearchdemo.config;

import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.List;

/**
 * es 查询中使用的通用工具类.
 *
 * @author idwangmo
 */
public class ElasticsearchTools {

    private ElasticsearchTools() {}

    public static MultiSearchRequest getRequest(String indexName, List<QueryBuilder> queryBuilders) {
        MultiSearchRequest request = new MultiSearchRequest();
        for (QueryBuilder queryBuilder : queryBuilders) {
            SearchRequest searchRequest = new SearchRequest(indexName);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(queryBuilder);
            searchRequest.source(searchSourceBuilder);
            request.add(searchRequest);
        }
        return request;
    }
}
