package com.example.tekupchatbot;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;
    private List<ChatMessage> messageList;
    private EditText editTextMessage;
    private ImageButton buttonSend;
    private ChatBot chatBot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupRecyclerView();
        setupChatBot();
        setupSendButton();

        // Message de bienvenue
        addBotMessage("Bonjour! Je suis votre assistant virtuel TEKUP. Comment puis-je vous aider aujourd'hui?");
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);
    }

    private void setupRecyclerView() {
        messageList = new ArrayList<>();
        chatAdapter = new ChatAdapter(messageList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(chatAdapter);
    }

    private void setupChatBot() {
        chatBot = new ChatBot();
    }

    private void setupSendButton() {
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String message = editTextMessage.getText().toString().trim();
        if (!message.isEmpty()) {
            // Ajouter le message de l'utilisateur
            addUserMessage(message);
            editTextMessage.setText("");

            // Obtenir et ajouter la réponse du bot
            String botResponse = chatBot.getResponse(message);
            addBotMessage(botResponse);

            // Faire défiler vers le bas
            recyclerView.smoothScrollToPosition(messageList.size() - 1);
        }
    }

    private void addUserMessage(String message) {
        messageList.add(new ChatMessage(message, true));
        chatAdapter.notifyItemInserted(messageList.size() - 1);
    }

    private void addBotMessage(String message) {
        messageList.add(new ChatMessage(message, false));
        chatAdapter.notifyItemInserted(messageList.size() - 1);
    }
}