package statemachine;

import java.util.HashMap;
import java.util.Objects;

public class ActionList<T> {
    
    private final HashMap<ActionKey, State<T>> actionMap;
    
    protected ActionList(){
        actionMap = new HashMap<>();
    }

    protected void addAction(Enum e, State<T> _source, State<T> _goal) {
        actionMap.put(new ActionKey(e,_source), _goal);
    }
    
    protected State<T> getGoal(Enum e, State<T> _source){
        return actionMap.get(new ActionKey(e,_source));
    }

    static class ActionKey {

        private final Enum action;
        private final State source;

        public ActionKey(Enum e, State _source) {
            action = e;
            source = _source;
        }

        public Enum getAction() {
            return action;
        }

        public State getSource() {
            return source;
        }

        @Override
        public boolean equals(Object obj) {
            boolean result = false;
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            } else {
                ActionKey key = (ActionKey) obj;
                if (key.getAction() == this.getAction() && key.getSource() == this.getSource()){
                    result = true;
                }
            }
            return result;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 67 * hash + Objects.hashCode(this.action);
            hash = 67 * hash + Objects.hashCode(this.source);
            return hash;
        }

    }
}
