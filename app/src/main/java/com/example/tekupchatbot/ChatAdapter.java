package com.example.tekupchatbot;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private List<ChatMessage> messageList;

    public ChatAdapter(List<ChatMessage> messageList) {
        this.messageList = messageList;
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat_message, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        ChatMessage message = messageList.get(position);
        holder.bind(message);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    class ChatViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewUserMessage, textViewBotMessage;
        private View userBubble, botBubble;

        public ChatViewHolder(View itemView) {
            super(itemView);
            textViewUserMessage = itemView.findViewById(R.id.textViewUserMessage);
            textViewBotMessage = itemView.findViewById(R.id.textViewBotMessage);
            userBubble = itemView.findViewById(R.id.userBubble);
            botBubble = itemView.findViewById(R.id.botBubble);
        }

        public void bind(ChatMessage message) {
            if (message.isUser()) {
                // Message de l'utilisateur
                textViewUserMessage.setText(message.getMessage());
                userBubble.setVisibility(View.VISIBLE);
                botBubble.setVisibility(View.GONE);
            } else {
                // Message du bot
                textViewBotMessage.setText(message.getMessage());
                userBubble.setVisibility(View.GONE);
                botBubble.setVisibility(View.VISIBLE);
            }
        }
    }
}