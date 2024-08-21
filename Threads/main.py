import random
import time
import threading
from ordening import Ordering

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



if __name__ == '__main__':
    num_names = 1000000
    filename = './names.txt'

    start_time = time.time()
    names = Gerador.generate_names(num_names)
    save_to_file(names, filename)
    end_time = time.time()
    print(f"Tempo de geração do arquivo: {end_time - start_time} segundos")

    start_time = time.time()
    with open(filename, 'r') as file:
        names = file.read().splitlines()
    end_time = time.time()
    print(f"Tempo de carregar os nomes do arquivo: {end_time - start_time} segundos")

    thread1 = threading.Thread(target=Ordering.sort_python, args=(names.copy(),))
    thread2 = threading.Thread(target=Ordering.quick_sort, args=(names.copy(),))
    thread2.start()
    thread1.start()
    thread1.join()
    thread2.join()

    print("Sem threads: ")
    start_time = time.time()
    Ordering.sort_python(names=names.copy())
    Ordering.quick_sort(lista=names.copy())
    end_time = time.time()
    print(f"Tempo de ordenação sem threads: {end_time - start_time} segundos")