![issues](https://img.shields.io/github/issues/Abstruck-Studio/QwQ-Library?style=for-the-badge) 
![forks](https://img.shields.io/github/forks/Abstruck-Studio/QwQ-Library?style=for-the-badge)
![stars](https://img.shields.io/github/stars/Abstruck-Studio/QwQ-Library?style=for-the-badge)
![license](https://img.shields.io/github/license/Abstruck-Studio/QwQ-Library?style=for-the-badge)
# QωQ-Library
A Fabric Lib Mod.  
You can register like this:

```java

public static final Registration<Item> ITEMS = new Registration<>(Registry.ITEM, MOD_ID);
public static final Item EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Settings()));

```

Block or other object are zhe same.
Don't forget, add this into your Main Class:

```java

@Override
public void onInitialize() {
  ItemInit.ITEMS.register();
}

```
If you want to Subscribe an Event, do it like this:

```java

@ModEvent
public class OnEventHandle {
    @SubscribeEvent
    public static void onEvent(IEvent event){
        //todo
    }
}

```
And don't forget to check the Environment of it.  

Moreover, you should add the path of all the events into your Main Class, just like this:

```java

@Override
public void onInitialize() {
  ItemInit.ITEMS.register();
  EventLoader.initEvent("org.abstruck.qwq.init.event");
}

```
If you put your event in __*std.init.event*__, it will be subscribe directly.  
__Remember! Don't mix Mixin Class with SubscribeEvent Class, it will error!__  


If you want to custom an Event, for example:

```java

public class AfterBreakEvent implements IEvent{
        private final World world;
        private final PlayerEntity player;
        private final BlockPos pos;
        private final BlockState state;
        private final BlockEntity blockEntity;
        private final ItemStack stack;
        public AfterBreakEvent(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
            this.world = world;
            this.player = player;
            this.pos = pos;
            this.state = state;
            this.blockEntity = blockEntity;
            this.stack = stack;
        }

        public PlayerEntity getPlayer() {
            return player;
        }

        public BlockPos getPos() {
            return pos;
        }
        
        public BlockState getState(){
            reutrn state;
        }

        public World getWorld() {
            return world;
        }

        public BlockEntity getBlockEntity() {
            return blockEntity;
        }

        public ItemStack getStack() {
            return stack;
        }
    }

```
Add Mixin like this:

```java

@Mixin(Block.class)
public abstract class BlockMixin {
    @Inject(method = "afterBreak", at = @At("RETURN"))
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack, CallbackInfo ci){
        EventManager.onEventAction(() -> new BlockEvent.AfterBreakEvent(world, player, pos, state, blockEntity, stack));
    }
}

```
