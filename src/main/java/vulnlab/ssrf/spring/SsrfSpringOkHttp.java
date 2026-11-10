package vulnlab.ssrf.spring;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.web.bind.annotation.*;

/** OkHttp : new Request.Builder().url(url). */
@RestController
public class SsrfSpringOkHttp {
    private final OkHttpClient client = new OkHttpClient();

    @GetMapping("/ssrf/ssrfspringokhttp")
    public String handle(@RequestParam String url) throws Exception {
        Request request = new Request.Builder().url(url).build();
        return client.newCall(request).execute().body().string();
    }
}
