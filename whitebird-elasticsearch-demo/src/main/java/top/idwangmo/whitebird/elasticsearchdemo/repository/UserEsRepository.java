package top.idwangmo.whitebird.elasticsearchdemo.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import top.idwangmo.whitebird.elasticsearchdemo.entity.UserEsEntity;

import java.util.List;

public interface UserEsRepository extends ElasticsearchRepository<UserEsEntity, String> {

    List<UserEsEntity> findByLastNameContains(String text);
}
