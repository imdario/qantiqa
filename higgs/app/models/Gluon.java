package models;

import java.util.List;

import siena.Column;
import siena.Id;
import siena.Index;
import siena.Max;
import siena.Model;
import siena.NotNull;
import siena.Query;
import siena.Table;

@Table("gluon")
public class Gluon extends Model {

    @Id
    public Long id;

    @Index("ix_host")
    @NotNull
    public String host;

    @NotNull
    public String secret;

    @NotNull
    public Boolean active;

    public Gluon(String host, String secret) {
        this.host = host;
        // TODO MD5
        this.secret = secret;
        this.active = Boolean.TRUE;
    }

    public static Query<Gluon> all() {
        return Model.all(Gluon.class);
    }

    public static Gluon findById(Long id) {
        return all().filter("id", id).get();
    }

    public static Gluon findByHost(String host) {
        return all().filter("host", host).get();
    }

    public static List<Gluon> active() {
        return all().filter("active", Boolean.TRUE).fetch();
    }

    public String toString() {
        return host;
    }
}