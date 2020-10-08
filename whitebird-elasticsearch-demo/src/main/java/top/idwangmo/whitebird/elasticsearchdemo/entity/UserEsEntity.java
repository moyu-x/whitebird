package top.idwangmo.whitebird.elasticsearchdemo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.elasticsearch.common.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import top.idwangmo.whitebird.elasticsearchdemo.config.ElasticsearchConstant;

@Document(indexName = ElasticsearchConstant.USER_INDEX)
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserEsEntity{

    @Id
    @Nullable
    private String id;

    @Field(value = ElasticsearchConstant.LAST_NAME_FIELD, type = FieldType.Keyword)
    private String lastName;

    @Field(value = ElasticsearchConstant.USER_TYPE_FIELD, type = FieldType.Keyword)
    private String userType;

    @Field(type = FieldType.Integer)
    private Integer age;
}
