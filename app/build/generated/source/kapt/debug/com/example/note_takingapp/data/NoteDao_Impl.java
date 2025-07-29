package com.example.note_takingapp.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import java.lang.Class;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class NoteDao_Impl implements NoteDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<Note> __insertAdapterOfNote;

  private final EntityDeleteOrUpdateAdapter<Note> __deleteAdapterOfNote;

  public NoteDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfNote = new EntityInsertAdapter<Note>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `notes` (`id`,`title`,`body`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Note entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getTitle());
        }
        if (entity.getBody() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getBody());
        }
      }
    };
    this.__deleteAdapterOfNote = new EntityDeleteOrUpdateAdapter<Note>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `notes` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Note entity) {
        statement.bindLong(1, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final Note note, final Continuation<? super Unit> $completion) {
    if (note == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfNote.insert(_connection, note);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object delete(final Note note, final Continuation<? super Unit> $completion) {
    if (note == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __deleteAdapterOfNote.handle(_connection, note);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public LiveData<List<Note>> getAll() {
    final String _sql = "SELECT * FROM notes ORDER BY id DESC";
    return __db.getInvalidationTracker().createLiveData(new String[] {"notes"}, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfBody = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "body");
        final List<Note> _result = new ArrayList<Note>();
        while (_stmt.step()) {
          final Note _item;
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpBody;
          if (_stmt.isNull(_columnIndexOfBody)) {
            _tmpBody = null;
          } else {
            _tmpBody = _stmt.getText(_columnIndexOfBody);
          }
          _item = new Note(_tmpId,_tmpTitle,_tmpBody);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
