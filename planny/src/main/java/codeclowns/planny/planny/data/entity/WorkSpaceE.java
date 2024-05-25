package codeclowns.planny.planny.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="Workspace")
public class WorkSpaceE {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workspace_id")
    private int workspaceId;

    @Column(name = "workspace_name", nullable = false)
    private String workspaceName;

    @Column(name = "short_name", unique = true, length = 63)
    private String shortName;

    @Column(name = "website", length = 128)
    private String website;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "is_enabled")
    private boolean isEnabled = true;

    @ManyToOne ( fetch = FetchType.LAZY,cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })

    @JoinColumn(name = "user_id", nullable = false)
    private UserE user;

    @OneToMany (mappedBy = "Workspace", fetch = FetchType.LAZY,cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })

    private List<Collaborator> danhSachCollaborators;

    @OneToMany (mappedBy = "Workspace", fetch = FetchType.LAZY,cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })

    private List<BoardE> danhSachBoard;

}
