package com.example.tekupchatbot;

import java.text.Normalizer;

public class ChatBot {

    public String getResponse(String userInput) {
        if (userInput == null || userInput.trim().isEmpty()) {
            return "Bonjour ! Pose-moi une question sur TEK-UP (formations, admission, localisation, frais, débouchés…).";
        }

        // Normalisation : minuscule, sans accents, apostrophes unifiées
        final String raw = userInput.trim();
        final String input = normalize(raw);

        // ===== Intent prioritaires (questions précises) =====

        // Localisation d'abord (pour éviter que la présence de "tek-up" déclenche la présentation)
        if (containsAny(input,  "ou se trouve", "adresse", "localisation", "situe", "situee", "campus", "carte", "map")) {
            return "📍 TEK-UP — 08 Rue Newton, Z.I. Chotrana II, Pôle Technologique Elgazala, Ariana 2088, Tunisie\n" +
                    "☎️ +216 23 814 000\n" +
                    "🌐 Site : https://tek-up.de";
        }
// Salutations
        if (containsAny(input, "salut", "bonjour", "coucou", "salem", "bsr", "bonsoir", "hey", "hello", "hi")) {
            return "👋 Bonjour et bienvenue chez TEK-UP ! Comment puis-je vous aider ?";
        }

// Au revoir
        if (containsAny(input, "bye", "au revoir", "a bientot", "a plus", "ciao", "tchao", "goodbye")) {
            return "👋 Merci de m'avoir contacté ! À bientôt et bonne journée 🌟";
        }

        // Licence / Master
        if (containsAny(input, "licence", "license", "master", "mastere", "m1", "m2", "lmd")) {
            return "❌ TEK-UP ne propose **ni Licence ni Mastère**.\n" +
                    "✅ TEK-UP délivre **le Diplôme National d’Ingénieur** (informatique / télécommunications).";
        }

        // Frais, tarifs, paiement
        if (containsAny(input, "frais", "prix", "cout", "tarif", "combien", "paiement", "payer", "mensualite", "m2", "m3", "differe")) {
            return "💳 Frais & paiement TEK-UP :\n" +
                    "• Modèle possible de **paiement différé** (M2/M3)\n" +
                    "• **Certifications internationales incluses** dans les frais\n" +
                    "Pour le détail des montants, contacte l’administration ou consulte le site officiel.";
        }

        // Admission / Inscription
        if (containsAny(input, "admission", "inscription", "s'inscrire", "sinscrire", "comment s'inscrire", "condition", "dossier", "concours", "prepa", "cycle preparatoire")) {
            return "📝 Admission TEK-UP :\n" +
                    "• Baccalauréat scientifique/technique → cycle préparatoire intégré\n" +
                    "• Ou évaluation/concours pour accès au cycle ingénieur\n" +
                    "• Dossier complet + entretien\n" +
                    "📅 Les inscriptions se tiennent généralement de juin à septembre.";
        }

        // Certifications
        if (containsAny(input, "certification", "certifications", "google", "amazon", "aws", "cisco", "oracle", "ibm")) {
            return "🎓 Les **certifications internationales** (Google, Amazon/AWS, Cisco, Oracle, IBM, …) sont **incluses** dans le parcours TEK-UP.";
        }

        // Stages / alternance / emploi du temps des études pratiques
        if (containsAny(input, "stage", "stages", "internship", "summer training", "alternance", "apprentissage")) {
            return "🧪 TEK-UP organise des **stages / summer trainings** et s’appuie sur ses partenariats entreprises pour faciliter l’immersion et l’employabilité.";
        }

        // Debouchés / carrière
        if (containsAny(input, "debouche", "debouches", "carriere", "emploi", "travail", "metier", "jobs")) {
            return "🚀 Débouchés typiques :\n" +
                    "• Ingénieur logiciel / télécoms\n" +
                    "• Architecte cloud / cybersécurité\n" +
                    "• Consultant SI / Data / DevOps\n" +
                    "• Chef de projet IT / Entrepreneur";
        }

        // Vie étudiante
        if (containsAny(input, "vie etudiante", "club", "clubs", "activite", "evenement", "hackathon", "competition", "asso", "association")) {
            return "🎉 Vie étudiante : clubs scientifiques/tech, hackathons & compétitions, événements culturels & sportifs, réseau étudiant actif.";
        }

        // Contact
        if (containsAny(input, "contact", "telephone", "tel", "email", "mail", "joindre", "numero", "numero de telephone")) {
            return "📞 **Contact TEK-UP**\n" +
                    "• Téléphone : +216 23 814 000\n" +
                    "• Adresse : 08 Rue Newton, Ariana 2088, Tunisie\n" +
                    "• Site : https://tek-up.de";
        }

        // Horaires (réponse volontairement générique pour éviter l’erreur si ça change)
        if (containsAny(input, "horaire", "heures", "ouverture", "ouvert", "ferme")) {
            return "🕘 Horaires administratifs : du **lundi au vendredi (heures de bureau)**. Pour les périodes exactes, vérifie le site ou appelle le standard.";
        }

        // Présentation générale (on laisse APRES les intents précis)
        if (containsAny(input, "tekup", "tek-up", "qu'est ce que", "quest ce que", "presentation", "universite", "qui etes vous", "tu es qui", "cest quoi tekup", "cest quoi tek-up")) {
            return "ℹ️ **TEK-UP** (Université Privée des Technologies & d’Ingénierie) — créée en **2014**, située à **Ariana (Pôle Technologique Elgazala)**.\n" +
                    "Spécialisation : **ingénierie informatique et sciences du numérique** (cycle ingénieur uniquement).";
        }

        // Salutations
        if (containsAny(input, "bonjour", "salut", "hello", "bonsoir", "hi", "slt")) {
            return "Bonjour 👋 Je réponds à tes questions sur **TEK-UP** (formations, admission, localisation, frais, débouchés, vie étudiante…).";
        }

        // Remerciements / au revoir
        if (containsAny(input, "merci", "thank you", "thanks")) {
            return "Avec plaisir ! Tu peux me poser d’autres questions sur TEK-UP.";
        }
        if (containsAny(input, "au revoir", "bye", "goodbye", "a bientot", "a plus", "ciao")) {
            return "Au revoir ! Bonne continuation 🚀";
        }

        // Réponse par défaut
        return "Je ne suis pas sûr d’avoir compris 🤔. Tu peux me demander :\n" +
                "• Formations (ingénieur uniquement) et certifications\n" +
                "• Admission / inscriptions\n" +
                "• Localisation / contact\n" +
                "• Frais & paiement différé (M2/M3)\n" +
                "• Débouchés / stages / vie étudiante";
    }

    // ===== Utils =====
    private static String normalize(String s) {
        String noAccents = Normalizer.normalize(s, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return noAccents
                .toLowerCase()
                .replace('’', '\'')
                .replaceAll("[!?.,;:()\\[\\]{}]", " ")
                .replaceAll("\\s+", " ")
                .trim();
    }

    private static boolean containsAny(String text, String... keywords) {
        for (String k : keywords) {
            if (text.contains(k)) return true;
        }
        return false;
    }
}
