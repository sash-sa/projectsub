package sub.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "configuration")
public class Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "issue_status_create")
    private IssueStatus issueStatus;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public IssueStatus getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(IssueStatus issueStatus) {
        this.issueStatus = issueStatus;
    }
}
