package sub.db.dto;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public interface IssueDto {
    String getFio();

    String getPhone();

    String getAddress();


    Date getCreated();

    Date getUpdate();

    String getTheme();

    String getDescription();

    @Value("#{target.issueStatus}")
    IssueStatusDto getIssueStatus();

    @Value("#{target.worker}")
    WorkerDto getWorker();

    interface IssueStatusDto {
        Long getId();
    }

    interface WorkerDto {
        Long getId();
    }
}
