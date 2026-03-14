package org.service.logging;

import org.model.LogEntry;
import org.model.LogLevel;
import org.observer.LogChangeListener;

import java.lang.ref.WeakReference;
import java.util.*;


public class LogBuffer {
    private final int m_iQueueLength;
    
    private final Deque<LogEntry> m_messages = new ArrayDeque<>();
    private final List<WeakReference<LogChangeListener>> m_listeners;

    public LogBuffer(int iQueueLength) {
        m_iQueueLength = iQueueLength;
        m_listeners = new ArrayList<>();
    }
    
    public void registerListener(LogChangeListener listener) {
        synchronized(m_listeners) {
            m_listeners.add(new WeakReference<>(listener));
        }
    }
    
    public void unregisterListener(LogChangeListener listener) {
        synchronized(m_listeners) {
            // Заодно чистим "мертвые" ссылки
            m_listeners.removeIf(w -> w.get() == listener || w.get() == null);
        }
    }
    
    public void append(LogLevel logLevel, String strMessage)
    {
        synchronized(m_messages) {
            if (m_messages.size() >= m_iQueueLength) {
                m_messages.removeFirst();
            }
            m_messages.addLast(new LogEntry(logLevel, strMessage));
        }

        notifyListeners();
    }

    private void notifyListeners() {
        synchronized(m_listeners) {
            // Возникает резонный вопрос: что здесь делает итератор и почему мы не используем обычный цикл?
            // Ответ на него прост: через итератор проще удалять элементы коллекции, а нам это нужно так как
            // Мы храним лишь soft ссылки и вполне вероятна ситуация, при которой объект стал неактуален
            Iterator<WeakReference<LogChangeListener>> it = m_listeners.iterator();
            while (it.hasNext()) {
                LogChangeListener listener = it.next().get();

                // Может произойти такая ситуация,
                // при которой все hard ссылки на объект пропали и мы получим null
                // В таком случае объект больше не актуален и его стоит удалить
                if (listener == null) {
                    it.remove(); //
                } else {
                    listener.onLogChanged();
                }
            }
        }
    }
    
    public int size() {
        synchronized(m_messages) {
            return m_messages.size();
        }
    }

    public Iterable<LogEntry> range(int startFrom, int count) {
        if (startFrom < 0 || startFrom >= m_messages.size()) return Collections.emptyList();
        return m_messages.stream()
            .skip(startFrom)
            .limit(count)
            .toList();
    }

    public Iterable<LogEntry> all() {
        synchronized (m_messages) {
            return new ArrayList<>(m_messages);
        }
    }
}
