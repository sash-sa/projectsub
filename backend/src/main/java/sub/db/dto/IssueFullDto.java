package sub.db.dto;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public interface IssueFullDto {
    Long getId();

    String getFio();

    String getPhone();

    String getAddress();


    Date getCreated();

    Date getUpdate();

    Date getTimeExecution();

    String getTheme();

    String getDescription();

    String getDecision();

    @Value("#{target.issueStatus}")
    IssueStatusForIssueFull getIssueStatus();

    @Value("#{target.worker}")
    WorkerForIssueFull getWorker();

    @Value("#{target.user}")
    UserDto getUser();

    interface IssueStatusDto {
        Long getId();
    }

    interface WorkerDto {
        Long getId();
    }
}
