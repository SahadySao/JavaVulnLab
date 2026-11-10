package vulnlab.common;

/** Service externe dont Joern ne voit pas le code (simule une bibliothèque sans jar). */
public interface Notifier {
    void notify(String message);
}
