import net.bytebuddy.ByteBuddy;
import net.bytebuddy.NamingStrategy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;

public class Test {
    public static void main(String[] args) {
        DynamicType.Unloaded<Object> dynamicType = new ByteBuddy()
                .with(new NamingStrategy.AbstractBase() {
                    @Override
                    protected String name(TypeDescription typeDescription) {
                        return "i.love.ByteBuddy." + typeDescription.getSimpleName();
                    }
                })
                .subclass(Object.class)
                .make();
        DynamicType.Unloaded<Object> dynamicType1 = new ByteBuddy()
                .with(new NamingStrategy.AbstractBase() {
                    @Override
                    protected String name(TypeDescription typeDescription) {
                        return "i.love.ByteBuddy." + typeDescription.getSimpleName();
                    }
                })
                .subclass(Object.class)
                .make();
        System.out.println(dynamicType.getTypeDescription().getCanonicalName());
        System.out.println(dynamicType1.getTypeDescription().getCanonicalName());

        ByteBuddy byteBuddy = new ByteBuddy();
        byteBuddy.with(new NamingStrategy.SuffixingRandom("suffix"));
        DynamicType.Unloaded<Object> dynamicType2 = byteBuddy.subclass(Object.class).make();
        System.out.println(dynamicType2.getTypeDescription().getCanonicalName());

        ByteBuddy byteBuddy1 = new ByteBuddy();
        DynamicType.Unloaded<Object> dynamicType3 = byteBuddy1.with(new NamingStrategy.SuffixingRandom("suffix"))
                .subclass(Object.class).make();
        System.out.println(dynamicType3.getTypeDescription().getCanonicalName());

        ByteBuddy byteBuddy2 = new ByteBuddy();
        ByteBuddy suffix = byteBuddy2.with(new NamingStrategy.SuffixingRandom("suffix"));
        DynamicType.Unloaded<Object> dynamicType4 = suffix.subclass(Object.class).make();
        System.out.println(dynamicType4.getTypeDescription().getCanonicalName());
    }
}
