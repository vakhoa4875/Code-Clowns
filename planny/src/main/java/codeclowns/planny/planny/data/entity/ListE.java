package codeclowns.planny.planny.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="List")
public class ListE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="list_id")
    private Integer listId;
    @Column(name="title", length=63, nullable=false)
    private String title;
    @Column(name="ordinal_nummeral", length=63, nullable=false)
    private int ordinalNummeral;
    @Column(name="is_enable", nullable = false)
    private Boolean isEnable;

    @OneToMany(mappedBy = "listE", cascade = {
            CascadeType.ALL,
    })
    private List<CardE> cardEList;

}
