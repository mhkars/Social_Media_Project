package com.halit.repository.entity;


import com.halit.repository.enums.Roles;
import com.halit.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
/**
 * Auth entitiy admin code ile status
 * status enum olacak
 * register dtoda admin code alaca��z
 * controllerm�zda ki register metodunu get mappingden postmappinge �ekelim
 * ve bir kullan�c� kaydedelim
 *
 *2-username uniq olsun
 * exception pakateini ekleyelim
 * admin code belirleyip register metodumuzu ona g�re g�ncelleyelim
 * (admin code do�ruysa rolemuz admin olsun)
 * Mapper paketimizi yaratal�m ve IuserMapper olu�tural�m
 * exceptionlar�da gerekli yerlerde kullanal�m
 *3- Validsyon i�in gerekli ba��ml�l�klar� ekleyelim ve register reguestdto ya
 * val�dasyon kosullara� ekleyel�m  pasword boyutunu belirleyeim username boyutu
 * swqgger� da ekleyelim
 *
 * 4- login metodu yazal�m controller ve servicede
 * login i�in bir request dto olu�tural�m
 * birde response dto olustural�m
 * bu olu�turdumuz dtolar�da metodumuzda kullanal�m
 *
 */
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String username;
    private  String password;
    private String email;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Roles role=Roles.USER;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status=Status.PENDING;
    private String activatedCode;

}
