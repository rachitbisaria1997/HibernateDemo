package entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // other type is SequenceGenerator
    //@SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")


    // other type is TableGenerator
    //@TableGenerator(name = "user_gen", table = "id_gen", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.TABLE, generator = "user_gen")
    private long studentId;

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Column(name = "student_name", length = 100, unique = true)
    private String name;

    @Column(name = "student_college", length = 200, nullable = true)
    private String college;

    private String phone;

    private String fatherName;

    private boolean active = true;

    @Lob
    private String about;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Certificate> certificates = new ArrayList<>();

    // associated data is not loaded immd in lazy. its loaded only when accessed using a sql query. improves performance by avoiding unn. data fetching

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "dept_id")
//    private Department department;
    // associated data is fetched immd, along with parent. one join query is executed by default;

//    @OneToMany(fetch = FetchType.LAZY)
//    private List<Order> orders;
//
//    public List<Order> getOrders() {
//        return orders;
//    }

    //order list wont be fetched from DB until we call it from an active hibernate session.
    // The orders list won't be fetched from the database until you actually call getOrders() and iterats or access it
    // like inside @Transactional method else it returns LazyInitializationException


}
