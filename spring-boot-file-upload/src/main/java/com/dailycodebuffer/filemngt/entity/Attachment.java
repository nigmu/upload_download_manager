package com.dailycodebuffer.filemngt.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

// id , filename , filetype , data(byte form)

@Entity
@Data // lombok annotation -> for getters , setters methods
@NoArgsConstructor
public class Attachment {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String fileName;
        private String fileType;

        @Lob // large object (LOB) in the database.
        @Column(name = "data", columnDefinition = "LONGBLOB")
        private byte[] data;


    public Attachment(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }
}
