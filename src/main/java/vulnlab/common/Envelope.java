package vulnlab.common;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/** Enveloppe JSON dont le contenu est polymorphe : le JSON choisit la classe ("@class"). */
public class Envelope {
    public String type;

    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
    public Object payload;
}
