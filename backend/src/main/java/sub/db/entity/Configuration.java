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
    private IssueStatus issueStatusStart;

    @ManyToOne
    @JoinColumn(name = "issue_status_closed")
    private IssueStatus issueStatusClosed;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public IssueStatus getIssueStatusStart() {
        return issueStatusStart;
    }

    public void setIssueStatusStart(IssueStatus issueStatusStart) {
        this.issueStatusStart = issueStatusStart;
    }

    public IssueStatus getIssueStatusClosed() {
        return issueStatusClosed;
    }

    public void setIssueStatusClosed(IssueStatus issueStatusClosed) {
        this.issueStatusClosed = issueStatusClosed;
    }
}
