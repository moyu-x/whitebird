package top.idwangmo.whitebird.elasticsearchdemo;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import top.idwangmo.whitebird.elasticsearchdemo.config.ElasticsearchConstant;
import top.idwangmo.whitebird.elasticsearchdemo.config.ElasticsearchTools;
import top.idwangmo.whitebird.elasticsearchdemo.entity.UserEsEntity;
import top.idwangmo.whitebird.elasticsearchdemo.repository.UserEsRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

class WhitebirdElasticsearchDemoApplicationTests {

	private final RestHighLevelClient highLevelClient;

	private final ElasticsearchOperations elasticsearchOperations;

	private final UserEsRepository userEsRepository;

	private final ElasticsearchRestTemplate elasticsearchRestTemplate;

	public WhitebirdElasticsearchDemoApplicationTests(RestHighLevelClient highLevelClient,
													  ElasticsearchOperations elasticsearchOperations,
													  UserEsRepository userEsRepository,
													  ElasticsearchRestTemplate elasticsearchRestTemplate) {
		this.highLevelClient = highLevelClient;
		this.elasticsearchOperations = elasticsearchOperations;
		this.userEsRepository = userEsRepository;
		this.elasticsearchRestTemplate = elasticsearchRestTemplate;
	}

	@Test
	void testESClient() throws IOException {
		GetRequest getRequest = new GetRequest(ElasticsearchConstant.USER_INDEX, "3333");
		GetResponse getResponse = highLevelClient.get(getRequest, RequestOptions.DEFAULT);
		System.out.println(getResponse.getIndex());
		System.out.println(getResponse.toString());
	}

	@Test
	void testMSearch() throws IOException {
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(ElasticsearchConstant.LAST_NAME_FIELD, "2");
		RangeQueryBuilder ageRange = QueryBuilders.rangeQuery(ElasticsearchConstant.AGE).from(1).to(20);
		queryBuilder.must(matchQueryBuilder).must(ageRange);

		BoolQueryBuilder queryBuilder2 = QueryBuilders.boolQuery();
		MatchQueryBuilder matchQueryBuilder2 = QueryBuilders.matchQuery(ElasticsearchConstant.USER_TYPE_FIELD, "2");
		RangeQueryBuilder ageRange2 = QueryBuilders.rangeQuery(ElasticsearchConstant.AGE).from(1).to(20);
		queryBuilder2.must(matchQueryBuilder2).must(ageRange2);

		MultiSearchRequest request = ElasticsearchTools.getRequest(ElasticsearchConstant.USER_INDEX, List.of(queryBuilder, queryBuilder2));
		MultiSearchResponse msearch = highLevelClient.msearch(request, RequestOptions.DEFAULT);
		System.out.println(Arrays.stream(msearch.getResponses()).map(MultiSearchResponse.Item::getResponse));
	}

	@Test
	void testGetIndexInfo() {
		IndexOperations indexOperations = elasticsearchOperations.indexOps(UserEsEntity.class);
		Map<String, Object> settings = indexOperations.getSettings();
		settings.keySet().forEach(System.out::println);
	}

	@Test
	public void testSave() {
		UserEsEntity user = new UserEsEntity();
		user.setLastName("测试1");
		user.setUserType("测试");

		IndexCoordinates indexCoordinates = elasticsearchOperations.getIndexCoordinatesFor(user.getClass());

		IndexQuery indexQuery = new IndexQueryBuilder().withId(user.getId()).withObject(user).build();

		String documentId = elasticsearchOperations.index(indexQuery, indexCoordinates);

		System.out.println(documentId);
	}

	@Test
	public void testElasticsearchOperations() {
		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(new MatchAllQueryBuilder())
				.build();

		System.out.println(searchQuery.getQuery().toString());

		SearchHits<UserEsEntity> searchHits = elasticsearchOperations.search(searchQuery, UserEsEntity.class);
		long count = searchHits.getTotalHits();
		System.out.println(count);

		List<SearchHit<UserEsEntity>> list = searchHits.getSearchHits();
		list.forEach(System.out::println);
	}

	@Test
	void testSave2() {
		UserEsEntity entity = new UserEsEntity();
		entity.setId(UUID.randomUUID().toString());
		entity.setLastName("测试2");
		entity.setUserType("还是测试");
		userEsRepository.save(entity);
	}

	@Test
	void testNormalQuery() {
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

		MatchQueryBuilder matchQuery = QueryBuilders.matchQuery(ElasticsearchConstant.LAST_NAME_FIELD, "测试");
		queryBuilder.must(matchQuery);

		System.out.println(queryBuilder.toString());

		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(queryBuilder)
				.build();

		SearchHits<UserEsEntity> search = elasticsearchOperations.search(searchQuery, UserEsEntity.class);
		search.forEach(hits -> {
			UserEsEntity content = hits.getContent();
			System.out.println(content.toString());
		});
	}

	@Test
	void queryMatchList() {
		List<UserEsEntity> result = userEsRepository.findByLastNameContains("测试");
		result.forEach(System.out::println);
	}

	@Test
	void testElasticsearchRestTemplate() {
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
				.must(QueryBuilders.matchQuery(ElasticsearchConstant.LAST_NAME_FIELD, "测试"));

		System.out.println(queryBuilder.toString());

		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(queryBuilder)
				.build();

		SearchHits<UserEsEntity> search = elasticsearchRestTemplate.search(searchQuery, UserEsEntity.class);
		search.forEach(System.out::println);
	}

	@Test
	void deleteTest() {
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
				.must(QueryBuilders.matchQuery(ElasticsearchConstant.LAST_NAME_FIELD, "2"));

		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(queryBuilder)
				.build();

		elasticsearchRestTemplate.delete(searchQuery, UserEsEntity.class, IndexCoordinates.of(ElasticsearchConstant.USER_INDEX));
	}

	@Test
	void testAggregations() {

		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(ElasticsearchConstant.LAST_NAME_FIELD, "2");
		RangeQueryBuilder ageRange = QueryBuilders.rangeQuery(ElasticsearchConstant.AGE).from(1).to(20);

		queryBuilder.must(matchQueryBuilder).must(ageRange);

		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(queryBuilder)
				.withSearchType(SearchType.DEFAULT)
				.addAggregation(AggregationBuilders.terms("subjects").field(ElasticsearchConstant.LAST_NAME_FIELD))
				.build();

		System.out.println(searchQuery.getQuery().toString());
		SearchHits<UserEsEntity> search = elasticsearchRestTemplate.search(searchQuery, UserEsEntity.class, IndexCoordinates.of("user_index"));

		search.forEach(System.out::println);
	}
}
