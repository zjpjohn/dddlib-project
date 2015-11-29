package org.dayatang.domain.event;

import java.util.Comparator;

/**
 * Compares two event classes based on their position in a class hierarchy.  Classes higher up in a hierarchy are
 * 'greater than' (ordered later) than classes lower in a hierarchy (ordered earlier).  Classes in unrelated
 * hierarchies have the same order priority.
 * <p/>
 * Event bus implementations use this comparator to determine which event listener method to invoke when polymorphic
 * listener methods are defined:
 * <p/>
 * If two event classes exist A and B, where A is the parent class of B (and B is a subclass of A) and an event
 * subscriber listens to both events:
 * <pre>
 * &#64;Subscribe
 * public void onEvent(A a) { ... }
 *
 * &#64;Subscribe
 * public void onEvent(B b) { ... }
 * </pre>
 *
 * The {@code onEvent(B b)} method will be invoked on the subscriber and the
 * {@code onEvent(A a)} method will <em>not</em> be invoked.  This is to prevent multiple dispatching of a single event
 * to the same consumer.
 * <p/>
 * The EventClassComparator is used to order listener method priority based on their event argument class - methods
 * handling event subclasses have higher precedence than superclasses.
 *
 */
public class EventClassComparator implements Comparator<Class> {

    @SuppressWarnings("unchecked")
    public int compare(Class a, Class b) {
        if (a == null) {
            if (b == null) {
                return 0;
            } else {
                return -1;
            }
        } else if (b == null) {
            return 1;
        } else if (a == b || a.equals(b)) {
            return 0;
        } else {
            if (a.isAssignableFrom(b)) {
                return 1;
            } else if (b.isAssignableFrom(a)) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
