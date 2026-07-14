package com.findex.team02.indexinfo.entity;

import com.findex.team02.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;


// 지수 정보를 저장하는 핵심 엔티티.
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA는 기본 생성자가 필요하지만, 외부에서 new IndexInfo()로 직접 생성하는 것을 막기 위해 PROTECTED로 설정
@Entity
@Table(
    name = "index_infos",
    uniqueConstraints = {
        // 지수 분류명 + 지수명 조합의 중복 등록을 DB 레벨에서 방지
        @UniqueConstraint(
            name = "uk_index_classification_name",
            columnNames = {"index_classification", "index_name"}
        )
    }
)
public class IndexInfo extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "index_classification", nullable = false)
  private String indexClassification; //지수 분류


  @Column(name = "index_name", nullable = false)
  private String indexName; //지수명

  @Column(nullable = false)
  private Integer employedItemsCount; //포함 종목 수

  @Column(nullable = false)
  private LocalDate basePointInTime; //기준 시점

  @Column(precision = 10, scale = 2, nullable = false)
  private BigDecimal baseIndex; //기준 지수

  // DB에 ENUM 이름(문자열)으로 저장한다. (숫자 저장 시 의미 파악이 어렵기 때문)
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private SourceType sourceType; //데이터출처

  // Lombok의 @Getter가 자동으로 isFavorite() 메서드를 생성해 준다.
  @Column(nullable = false)
  private Boolean favorite; //즐겨찾기

  // 지수 정보 객체를 생성하는 생성자.

   /* @Builder를 사용하면 new IndexInfo(...) 대신
   * IndexInfo.builder().indexClassification("코스피").indexName("코스피 200").build() 형태로
   * 어떤 값을 어떤 필드에 넣는지 명확하게 알 수 있어 가독성이 높아진다.
   */
  @Builder
  public IndexInfo(String indexClassification, String indexName, Integer employedItemsCount,
      LocalDate basePointInTime, BigDecimal baseIndex, SourceType sourceType, Boolean favorite) {
    this.indexClassification = indexClassification;
    this.indexName = indexName;
    this.employedItemsCount = employedItemsCount;
    this.basePointInTime = basePointInTime;
    this.baseIndex = baseIndex;
    this.sourceType = sourceType;
    this.favorite = favorite;
  }
  // 수정 가능한 지수 정보 필드를 업데이트하는 메서드.
  public void updateInfo(Integer employedItemsCount, LocalDate basePointInTime,
      BigDecimal baseIndex, Boolean favorite) {
    this.employedItemsCount = employedItemsCount;
    this.basePointInTime = basePointInTime;
    this.baseIndex = baseIndex;
    this.favorite = favorite;
  }
}
