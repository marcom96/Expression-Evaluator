/**   
   @author       Marco Martinez
   @fileName     GenericItemType.java
   @version      1.0
   @description  Used in Container class as the "only" data type.  
   @date         12/18/2018

   Program Change Log 
   ==========================
   Name     Date     Description
   Marco    12/18    Create baseline for GenericItemType.
 */
 
public abstract class GenericItemType {

   // (+) abstract boolean isLess(GenericItemType git)
   public abstract boolean isLess(GenericItemType git);

   // (+) abstract boolean isEqual(GenericItemType git)
   public abstract boolean isEqual(GenericItemType git);

   // (+) abstract boolean isGreater(GenericItemType git)
   public abstract boolean isGreater(GenericItemType git);
}
