package vulnlab.common;

import java.io.Serializable;

/** Objet métier attendu par les cas de désérialisation. */
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    public String id;
    public int quantity;
}
