package edu.cnm.deepdive.celestialbodies.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import edu.cnm.deepdive.celestialbodies.R;
import edu.cnm.deepdive.celestialbodies.model.entity.Star;
import java.text.DateFormat;
import java.util.List;

/**
 * <code>HistoryAdapter</code> provides a link between the database and UI display. Using the
 * the available categories within the {@link Star} entity as a reference to make the columns
 * (date and score) that are displayed when HistoryFragment is inflated for display for
 * the user.
 */
public class HistoryAdapter extends ArrayAdapter<Star> {

  private DateFormat dateFormat;

  /**
   * <code>HistoryAdapter</code> provides a link between the database and UI display. Using the
   * the available categories within the {@link Star} entity as a reference to make the columns
   * (date and score) that are displayed when HistoryFragment is inflated for display for
   * the user.
   *
   * @param context access to the database
   * @param items categories from Consumption (date, score)
   */

  public HistoryAdapter(@NonNull Context context, List<Star> items) {
    super(context, R.layout.history_item, items);
    dateFormat = android.text.format.DateFormat.getDateFormat(context);
    String starIdFormat = context.getString(R.string.star_format);
  }


  /**
   * <code>getView</code> converts the database items within {@link Star} entity class to
   * be viewed as a text.
   *
   * @param position position of each item.
   * @param convertView converts view.
   * @param parent group of Consumption
   * @return visual text of the items
   */
  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    View view = (convertView != null) ? convertView
        : LayoutInflater.from(getContext()).inflate(R.layout.history_item, parent, false);
    Star star = getItem(position);
    TextView date = view.findViewById(R.id.date);
    TextView starItem = view.findViewById(R.id.star_id);
    date.setText(dateFormat.format(star.getTime().getTime()));
    starItem.setText(star.getCatId());

    return view;
  }

}