package com.authentification.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;
    @Column(name="name")
    private String name ;

    @OneToMany(mappedBy = "category")
    private List<Annonce> annonces;

    public Long getCategory_id() {return category_id;}
    public String getName() {return name;}

    public void setCategory_id(Long category_id) {this.category_id = category_id;}

    public void setName(String name) {this.name = name;}

    public Category(Long category_id, String name) {this.category_id = category_id;
                                                    this.name = name;}
    public Category() {}

    @Override
    public String toString() {
        return "Category{" + "category_id=" + category_id + ", name='" + name + '\'' + '}';}
}
