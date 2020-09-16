NB: ce module ne comporte pas de fichier module-info.java à la racine
Si ce module est ultérieurement chargé via le --module-path
il sera alors traité comme un module automatique (de nom automatique mod.auto.s ou autre selon le nom du .jar)
avec une sémantique par défaut de type exports * et requires * 