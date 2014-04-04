package statemachine;

import java.util.HashMap;
import java.util.Objects;

public class ActionList<T> {
    
    private final HashMap<ActionKey, State<T>> actionMap;
    
    protected ActionList(){
        actionMap = new HashMap<>();
    }

    protected void addAction(Enum e, State<T> _source, State<T> _goal) {
        ActionKey actionKey = new ActionKey(e,_source);
//        System.out.println(actionKey.hashCode());
        actionMap.put(actionKey, _goal);
    }
    
    protected State<T> getGoal(Enum e, State<T> _source){
        ActionKey actionKey = new ActionKey(e,_source);
//        System.out.println(actionKey.hashCode());
        return actionMap.get(actionKey);
    }

    static class ActionKey {

        private final Enum action;
        private final State source;

        public ActionKey(Enum e, State _source) {
            action = e;
            source = _source;
        }

//        public Enum getAction() {
//            return action;
//        }
//
//        public State getSource() {
//            return source;
//        }

//        @Override
//        public boolean equals(Object obj) {
//            boolean result = false;
//            if (obj == null || obj.getClass() != getClass()) {
//                return false;
//            } else {
//                ActionKey key = (ActionKey) obj;
//                if (key.getAction() == this.getAction() && key.getSource() == this.getSource()){
//                    result = true;
//                }
//            }
//            return result;
//        }
//
//        @Override
//        public int hashCode() {
//            int hash = 0;
////            hash = hash + Objects.hashCode(this.action);
////            hash = hash + Objects.hashCode(this.source);
//            return hash;
//        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ActionKey actionKey = (ActionKey) o;

            if (!action.equals(actionKey.action)) return false;
            if (!source.equals(actionKey.source)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = action.hashCode();
            result = 31 * result + source.hashCode();
            return result;
        }
    }
}
