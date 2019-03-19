package assignment.coding.com.networkdataviewer.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import assignment.coding.com.networkdataviewer.R;
import assignment.coding.com.networkdataviewer.data.model.RecordsModel;

/**
 * This class is responsible for showing data received from api to {@link RecyclerView}.
 */
public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * This is the item type for view where data would be displayed.
     */
    private final int VIEW_TYPE_ITEM = 0;
    /**
     * List of {@link RecordsModel}.
     */
    private List<RecordsModel> recordsModelList;
    /**
     * Context.
     */
    private Context context;

    /**
     * Setter for recordsModelList.
     *
     * @param recordsModelList recordsModelList.
     */
    public void setRecordsModelList(List<RecordsModel> recordsModelList) {
        this.recordsModelList = recordsModelList;
        notifyDataSetChanged();
    }

    /**
     * Constructor.
     *
     * @param itemList {@link List<RecordsModel>}
     */
    public HomeRecyclerViewAdapter(List<RecordsModel> itemList) {
        recordsModelList = itemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            context = parent.getContext();
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof ItemViewHolder) {
            populateItemRows((ItemViewHolder) viewHolder, position);
        }

    }

    @Override
    public int getItemCount() {
        return recordsModelList == null ? 0 : recordsModelList.size();
    }

    /**
     * The following method decides the type of ViewHolder to display in the RecyclerView.
     */
    @Override
    public int getItemViewType(int position) {
        int VIEW_TYPE_LOADING = 1;
        return recordsModelList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;
        ImageView imageView;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            tvItem = itemView.findViewById(R.id.tvItem);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    /**
     * Populating rows.
     *
     * @param viewHolder view holder.
     * @param position   position.
     */
    public void populateItemRows(ItemViewHolder viewHolder, int position) {
        RecordsModel recordsModel = recordsModelList.get(position);
        String data = recordsModel.getTotalVolumeOfMobileData()
                + "";
        String isDec = recordsModel.getIsDecreaseInVolume();
        if (isDec != null && isDec.equals("true")) {
            viewHolder.imageView.setEnabled(true);
            viewHolder.imageView.setVisibility(View.VISIBLE);
        } else {
            viewHolder.imageView.setEnabled(false);
            viewHolder.imageView.setVisibility(View.INVISIBLE);

        }
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, context.getString(R.string.decrease_in_data), Toast.LENGTH_LONG).show();
            }
        });
        viewHolder.tvItem.setText(data);

    }


}
