import random

class Gerador:
    """
    Classe que implementa métodos para gerar nomes aleatórios e os salvar em um arquivo.
    """
    def generate_names(num_names):
        """
        Gera uma lista de nomes aleatórios.

        Args:
            num_names (int): Número de nomes a serem gerados.

        Returns:
            list: Lista de nomes gerados.
        """
        names = []
        for _ in range(num_names):
            name = ''.join(random.choices('abcdefghijklmnopqrstuvwxyz', k=5))
            names.append(name)
        return names

    def save_to_file(names, filename):
        """
        Salva uma lista de nomes em um arquivo.

        Args:
            names (list): Lista de nomes a ser salva.
            filename (str): Nome do arquivo onde os nomes serão salvos.
        """
        with open(filename, 'w') as file:
            for name in names:
                file.write(name + '\n')
