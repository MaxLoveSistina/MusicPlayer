package com.musicplayer.core.data.local;

import androidx.annotation.NonNull;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Integer;
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
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class FavoriteDao_Impl implements FavoriteDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<FavoriteEntity> __insertAdapterOfFavoriteEntity;

  public FavoriteDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfFavoriteEntity = new EntityInsertAdapter<FavoriteEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `favorites` (`songId`,`addedAt`) VALUES (?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final FavoriteEntity entity) {
        statement.bindLong(1, entity.getSongId());
        statement.bindLong(2, entity.getAddedAt());
      }
    };
  }

  @Override
  public Object addFavorite(final FavoriteEntity entity,
      final Continuation<? super Unit> $completion) {
    if (entity == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfFavoriteEntity.insert(_connection, entity);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Flow<List<FavoriteEntity>> getAllFavorites() {
    final String _sql = "SELECT * FROM favorites ORDER BY addedAt DESC";
    return FlowUtil.createFlow(__db, false, new String[] {"favorites"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfSongId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "songId");
        final int _columnIndexOfAddedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "addedAt");
        final List<FavoriteEntity> _result = new ArrayList<FavoriteEntity>();
        while (_stmt.step()) {
          final FavoriteEntity _item;
          final long _tmpSongId;
          _tmpSongId = _stmt.getLong(_columnIndexOfSongId);
          final long _tmpAddedAt;
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt);
          _item = new FavoriteEntity(_tmpSongId,_tmpAddedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Object isFavorite(final long songId, final Continuation<? super Boolean> $completion) {
    final String _sql = "SELECT COUNT(*) > 0 FROM favorites WHERE songId = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, songId);
        final Boolean _result;
        if (_stmt.step()) {
          final Integer _tmp;
          if (_stmt.isNull(0)) {
            _tmp = null;
          } else {
            _tmp = (int) (_stmt.getLong(0));
          }
          _result = _tmp == null ? null : _tmp != 0;
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object removeFavorite(final long songId, final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM favorites WHERE songId = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, songId);
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
