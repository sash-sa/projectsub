package sub.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "issueStatus")
public class IssueStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    @Column(name = "visibleForIndex")
    private Boolean visibleForIndex;

    @Column(name = "nomer")
    private Integer nomer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getVisibleForIndex() {
        return visibleForIndex;
    }

    public void setVisibleForIndex(Boolean visibleForIndex) {
        this.visibleForIndex = visibleForIndex;
    }

    public Integer getNomer() {
        return nomer;
    }

    public void setNomer(Integer nomer) {
        this.nomer = nomer;
    }
}
