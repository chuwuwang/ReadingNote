package factory.method.demo;

public class AudiCarFactory extends AudiFactory {

    @SuppressWarnings("unchecked")
    @Override
    public <T extends AudiCar> T createAudiCar(Class<T> clazz) {
        AudiCar car = null;
        try {
            String clazzName = clazz.getName();
            car = (AudiCar) Class.forName(clazzName).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T) car;
    }

}
