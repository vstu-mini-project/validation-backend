package com.validation.model.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;


@Builder
@Entity
@Table(name = "document_types")
@NoArgsConstructor
@AllArgsConstructor
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "document_type_id")
    private Long id;
    @Column(nullable = false)
    private String typeName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentType that = (DocumentType) o;
        return Objects.equals(getId(), that.getId())
                && Objects.equals(getTypeName(), that.getTypeName()
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                getTypeName()
        );
    }

    @Override
    public String toString() {
        return "DocumentType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
