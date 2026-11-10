package vulnlab.common;

import java.util.List;

/** Fragment de repository Spring Data écrit à la main (interface + classe ...Impl). */
public interface TitleSearchRepository {
    List<?> findByTitleUnsafe(String title);
}
