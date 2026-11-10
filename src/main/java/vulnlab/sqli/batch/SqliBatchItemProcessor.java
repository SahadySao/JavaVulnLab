package vulnlab.sqli.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.jdbc.core.JdbcTemplate;

/** Batch : une ligne d'un fichier partenaire, concaténée dans une requête. Attendu dans « aVerifier ». */
public class SqliBatchItemProcessor implements ItemProcessor<String, String> {
    private final JdbcTemplate jdbcTemplate;

    public SqliBatchItemProcessor(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String process(String line) {
        String customer = line.split(";")[0];
        jdbcTemplate.update("UPDATE invoice SET status = 'IMPORTED' WHERE customer = '" + customer + "'");
        return line;
    }
}
