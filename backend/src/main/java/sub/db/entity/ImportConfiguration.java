package sub.db.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "import_configuration")
public class ImportConfiguration {
    @Id
    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "data")
    private byte[] data;

    public ImportConfiguration() {
    }

    public ImportConfiguration(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImportConfiguration that = (ImportConfiguration) o;
        return Objects.equals(name, that.name) &&
                Arrays.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
