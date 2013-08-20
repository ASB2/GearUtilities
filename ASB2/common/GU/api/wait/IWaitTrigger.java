package GU.api.wait;

public interface IWaitTrigger {

    public void trigger(int id);

    public boolean shouldTick(int id);
}
