package sub.db.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "worker")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "phone")
    private String phone;
    @ManyToMany
    @JoinTable(name = "worker_position",
            joinColumns = @JoinColumn(name = "workerId"),
            inverseJoinColumns = @JoinColumn(name = "positionId")
    )
    private List<Position> position;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Position> getPosition() {
        return position;
    }

    public void setPosition(List<Position> position) {
        this.position = position;
    }
}
