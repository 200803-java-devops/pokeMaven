import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class typesTest {

    @Test
    void getType() {
        int fire = types.getType("FIRE");
        assert (fire == types.FIRE);
    }

    @Test
    void typeName() {
        String type = types.typeName(1);
        assert (type.equals("FIRE"));
    }

    @Test
    void values() {
        assert (types.FIRE == 1);
    }

    @Test
    void valueOf() {

    }
}