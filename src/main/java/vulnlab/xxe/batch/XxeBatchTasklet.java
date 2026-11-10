package vulnlab.xxe.batch;

import java.io.FileInputStream;
import java.io.InputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/** Batch : le fichier d'état est déposé par un partenaire, puis analysé par un parseur StAX non protégé.
  * Aucune source HTTP : attendu dans la rubrique « aVerifier ». */
public class XxeBatchTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        String path = (String) chunkContext.getStepContext().getJobParameters().get("fichierEtat");
        try (InputStream inputEtat = new FileInputStream(path)) {
            XMLInputFactory xif = XMLInputFactory.newInstance();
            XMLStreamReader reader = xif.createXMLStreamReader(inputEtat);
            while (reader.hasNext()) {
                reader.next();
            }
        }
        return RepeatStatus.FINISHED;
    }
}
