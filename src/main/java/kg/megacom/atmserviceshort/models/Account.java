package kg.megacom.atmserviceshort.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    long account;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "balance_id")
    Balance balance;

    @ManyToOne
    @JoinColumn(name = "client_id")
    Client client;
}
