package codeclowns.planny.planny.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="User")
public class UserE {
    @Id
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "username", length = 63, unique = true)
    private String userName;

    @Column(name = "email", length = 63, unique = true)
    private String email;

    @Column(name = "fullname", length = 63)
    private String fullName;

    @Column(name="avatar",length = 127)
    private String avatar;

    @Column(name="dob")
    private Date dob;

    @Column(name="gender")
    private Byte gender;

    @Column(name="is_private")
    private Byte is_private;

    @OneToMany (mappedBy = "User", fetch = FetchType.LAZY,cascade = {
            CascadeType.ALL
    })

    private List<Collaborator> danhSachCollaborator;


    @OneToMany (mappedBy = "User", fetch = FetchType.LAZY,cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })

    private List<WorkSpaceE> danhSachWorkSpace;

      @OneToMany (mappedBy = "User", fetch = FetchType.LAZY,cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })

    private List<MemberE> danhSachMember;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "user_id")
    private AccountE account;
}