package ru.job4j.inheritance;
/**
 * @author Georg Rusanov (rusanovgeorgy@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Engineer extends Profession {
    public House build() {
        System.out.println(this.name + " построил дом");
        return new House();
    }
}
