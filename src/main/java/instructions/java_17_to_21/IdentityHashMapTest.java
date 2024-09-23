package instructions.java_17_to_21;

import java.util.IdentityHashMap;

/**
 * @author Maksim Urusov
 */
public record IdentityHashMapTest(String value) {

    public static void main(String[] args) {

        var users = new IdentityHashMap<String, IdentityHashMapTest>();
        String key = "АБЦ";

        // добавим комбинацию ключ-значение
        users.put(key, new IdentityHashMapTest("Сидели на трубе!"));

        // попытка удалить РАВНУЮ, но не ИДЕНТИЧНУЮ комбинацию
        var removed = users.remove(key, new IdentityHashMapTest("Сидели на трубе!"));

        // согласно контракту IdentityHashMap удаления не должно было быть!

        // ❌ до Java 19: ВНИМАНИЕ - будет выполненно УДАЛЕНИЕ!!
        // ✅ начиная с Java 20: удаления НЕ БУДЕТ так как объекты разные
        System.out.println(removed);
    }
}