"""
ASSIGNMENT 2 (Analysis Time Complexity for Boyer-Moore Algorithm)

MEMBER 1: ANDYCLOS A/L BOON MEE (22300738)
MEMBER 2: MUHAMMAD EZZAT HAZIQ BIN ELHAN (22300604)
MEMBER 3: KAM WENG XUAN (22300683)
MEMBER 4: LOH WEI TING (22300549)
"""

import pandas as pd
import matplotlib.pyplot as plt

# manually input data based on the run experiment
data = [
    {"text_size": 1000, "pattern_size": 10, "average_elapsed_time": 14},
    {"text_size": 1000, "pattern_size": 20, "average_elapsed_time": 20},
    {"text_size": 1000, "pattern_size": 30, "average_elapsed_time": 24},
    {"text_size": 1000, "pattern_size": 40, "average_elapsed_time": 17},
    {"text_size": 1000, "pattern_size": 50, "average_elapsed_time": 21},
    {"text_size": 2000, "pattern_size": 10, "average_elapsed_time": 19},
    {"text_size": 2000, "pattern_size": 20, "average_elapsed_time": 20},
    {"text_size": 2000, "pattern_size": 30, "average_elapsed_time": 29},
    {"text_size": 2000, "pattern_size": 40, "average_elapsed_time": 20},
    {"text_size": 2000, "pattern_size": 50, "average_elapsed_time": 32},
    {"text_size": 3000, "pattern_size": 10, "average_elapsed_time": 20},
    {"text_size": 3000, "pattern_size": 20, "average_elapsed_time": 23},
    {"text_size": 3000, "pattern_size": 30, "average_elapsed_time": 27},
    {"text_size": 3000, "pattern_size": 40, "average_elapsed_time": 21},
    {"text_size": 3000, "pattern_size": 50, "average_elapsed_time": 22},
    {"text_size": 4000, "pattern_size": 10, "average_elapsed_time": 20},
    {"text_size": 4000, "pattern_size": 20, "average_elapsed_time": 27},
    {"text_size": 4000, "pattern_size": 30, "average_elapsed_time": 23},
    {"text_size": 4000, "pattern_size": 40, "average_elapsed_time": 24},
    {"text_size": 4000, "pattern_size": 50, "average_elapsed_time": 26},
    {"text_size": 5000, "pattern_size": 10, "average_elapsed_time": 23},
    {"text_size": 5000, "pattern_size": 20, "average_elapsed_time": 20},
    {"text_size": 5000, "pattern_size": 30, "average_elapsed_time": 20},
    {"text_size": 5000, "pattern_size": 40, "average_elapsed_time": 23},
    {"text_size": 5000, "pattern_size": 50, "average_elapsed_time": 21},
]

# Convert the list of dictionaries to a DataFrame
df = pd.DataFrame(data)

# Plot the data
plt.figure(figsize=(10, 6))

# Create separate lines for each pattern size
for pattern_size in df['pattern_size'].unique():
    subset = df[df['pattern_size'] == pattern_size]
    plt.plot(subset['text_size'], subset['average_elapsed_time'], label=f'Pattern size: {pattern_size}')

plt.xlabel('Text Size')
plt.ylabel('Average Elapsed Time (ms)')
plt.title('Boyer-Moore Algorithm Time Complexity - O(nm)')
plt.legend()
plt.grid(True)
plt.show()
