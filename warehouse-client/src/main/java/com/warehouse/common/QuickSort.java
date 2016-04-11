package com.warehouse.common;

import java.util.List;

/*
 * Generic class For Sorting
 */
public final class QuickSort {
	public QuickSort() {
	}// QuickSort

	/**
	 * sort an int array
	 * 
	 * @param arr
	 *            [] int
	 */
	public void sort(int[] arr) {
		sort(arr, 0, arr.length - 1);
	}// sort (int[] arr)

	/**
	 * sort an int array
	 * 
	 * @param arr
	 *            [] int
	 * @param begin
	 *            int
	 * @param end
	 *            int
	 */
	public void sort(int[] arr, int begin, int end) {
		if (end > begin) {
			int low = begin;
			int high = end;
			int middle = arr[(begin + end) / 2];
			while (low <= high) {
				while (low < end && arr[low] < middle) {
					low++;
				}
				while (high > begin && arr[high] > middle) {
					high--;
				}
				if (low <= high) {
					int temp = arr[low];
					arr[low] = arr[high];
					arr[high] = temp;
					low++;
					high--;
				}
			}
			if (begin < high) {
				sort(arr, begin, high);
			}
			if (low < end) {
				sort(arr, low, end);
			}
		}
	}// sort (int[] arr, int begin, int end)

	/**
	 * sort an String array
	 * 
	 * @param arr
	 *            [] String
	 */
	public void sort(String[] arr) {
		sort(arr, 0, arr.length - 1);
	}// sort (String[] arr)

	/**
	 * sort an String array
	 * 
	 * @param arr
	 *            [] String
	 * @param begin
	 *            int
	 * @param end
	 *            int
	 */
	public void sort(String[] arr, int begin, int end) {
		if (end > begin) {
			int low = begin;
			int high = end;
			String middle = arr[(begin + end) / 2];
			while (low <= high) {
				while (low < end && arr[low].compareToIgnoreCase(middle) < 0) {
					low++;
				}
				while (high > begin && arr[high].compareToIgnoreCase(middle) > 0) {
					high--;
				}
				if (low <= high) {
					String temp = arr[low];
					arr[low] = arr[high];
					arr[high] = temp;
					low++;
					high--;
				}
			}
			if (begin < high) {
				sort(arr, begin, high);
			}
			if (low < end) {
				sort(arr, low, end);
			}
		}
	}// sort (String[] arr, int begin, int end)

	/**
	 * sort an long array
	 * 
	 * @param arr
	 *            [] long
	 * @param begin
	 *            int
	 * @param end
	 *            int
	 */
	public void sort(long[] arr) {
		sort(arr, 0, arr.length - 1);
	}// sort (long[] arr)

	/**
	 * sort an long array
	 * 
	 * @param arr
	 *            [] long
	 * @param begin
	 *            int
	 * @param end
	 *            int
	 */
	public void sort(long[] arr, int begin, int end) {
		if (end > begin) {
			int low = begin;
			int high = end;
			long middle = arr[(begin + end) / 2];
			while (low <= high) {
				while (low < end && arr[low] < middle) {
					low++;
				}
				while (high > begin && arr[high] > middle) {
					high--;
				}
				if (low <= high) {
					long temp = arr[low];
					arr[low] = arr[high];
					arr[high] = temp;
					low++;
					high--;
				}
			}
			if (begin < high) {
				sort(arr, begin, high);
			}
			if (low < end) {
				sort(arr, low, end);
			}
		}
	}// sort (long[] arr, int begin, int end)

	/**
	 * sort an short array
	 * 
	 * @param arr
	 *            [] short
	 */
	public void sort(short[] arr) {
		sort(arr, 0, arr.length - 1);
	}// sort (short[] arr)

	/**
	 * sort an short array
	 * 
	 * @param arr
	 *            [] short
	 * @param begin
	 *            int
	 * @param end
	 *            int
	 */
	public void sort(short[] arr, int begin, int end) {
		if (end > begin) {
			int low = begin;
			int high = end;
			short middle = arr[(begin + end) / 2];
			while (low <= high) {
				while (low < end && arr[low] < middle) {
					low++;
				}
				while (high > begin && arr[high] > middle) {
					high--;
				}
				if (low <= high) {
					short temp = arr[low];
					arr[low] = arr[high];
					arr[high] = temp;
					low++;
					high--;
				}
			}
			if (begin < high) {
				sort(arr, begin, high);
			}
			if (low < end) {
				sort(arr, low, end);
			}
		}
	}// sort (short[] arr, int begin, int end)

	/**
	 * sort an double array
	 * 
	 * @param arr
	 *            [] double
	 */
	public void sort(double[] arr) {
		sort(arr, 0, arr.length - 1);
	}// sort (double[] arr)

	/**
	 * sort an double array
	 * 
	 * @param arr
	 *            [] double
	 * @param begin
	 *            int
	 * @param end
	 *            int
	 */
	public void sort(double[] arr, int begin, int end) {
		if (end > begin) {
			int low = begin;
			int high = end;
			double middle = arr[(begin + end) / 2];
			while (low <= high) {
				while (low < end && arr[low] < middle) {
					low++;
				}
				while (high > begin && arr[high] > middle) {
					high--;
				}
				if (low <= high) {
					double temp = arr[low];
					arr[low] = arr[high];
					arr[high] = temp;
					low++;
					high--;
				}
			}
			if (begin < high) {
				sort(arr, begin, high);
			}
			if (low < end) {
				sort(arr, low, end);
			}
		}
	}// sort (double[] arr, int begin, int end)

	/**
	 * sort an float array
	 * 
	 * @param arr
	 *            [] float
	 */
	public void sort(float[] arr) {
		sort(arr, 0, arr.length - 1);
	}// sort (float[] arr)

	/**
	 * sort an float array
	 * 
	 * @param arr
	 *            [] float
	 * @param begin
	 *            int
	 * @param end
	 *            int
	 */
	public void sort(float[] arr, int begin, int end) {
		if (end > begin) {
			int low = begin;
			int high = end;
			float middle = arr[(begin + end) / 2];
			while (low <= high) {
				while (low < end && arr[low] < middle) {
					low++;
				}
				while (high > begin && arr[high] > middle) {
					high--;
				}
				if (low <= high) {
					float temp = arr[low];
					arr[low] = arr[high];
					arr[high] = temp;
					low++;
					high--;
				}
			}
			if (begin < high) {
				sort(arr, begin, high);
			}
			if (low < end) {
				sort(arr, low, end);
			}
		}
	}// sort (float[] arr, int begin, int end)

	/**
	 * sort an char array
	 * 
	 * @param arr
	 *            [] char
	 */
	public void sort(char[] arr) {
		sort(arr, 0, arr.length - 1);
	}// sort (char[] arr)

	/**
	 * sort an char array
	 * 
	 * @param arr
	 *            [] char
	 * @param begin
	 *            int
	 * @param end
	 *            int
	 */
	public void sort(char[] arr, int begin, int end) {
		if (end > begin) {
			int low = begin;
			int high = end;
			char middle = arr[(begin + end) / 2];
			while (low <= high) {
				while (low < end && arr[low] < middle) {
					low++;
				}
				while (high > begin && arr[high] > middle) {
					high--;
				}
				if (low <= high) {
					char temp = arr[low];
					arr[low] = arr[high];
					arr[high] = temp;
					low++;
					high--;
				}
			}
			if (begin < high) {
				sort(arr, begin, high);
			}
			if (low < end) {
				sort(arr, low, end);
			}
		}
	}// sort (char[] arr, int begin, int end)

	/**
	 * sort an byte array
	 * 
	 * @param arr
	 *            [] byte
	 */
	public void sort(byte[] arr) {
		sort(arr, 0, arr.length - 1);
	}// void sort (byte[] arr)

	/**
	 * sort an byte array
	 * 
	 * @param arr
	 *            [] byte
	 * @param begin
	 *            int
	 * @param end
	 *            int
	 */
	public void sort(byte[] arr, int begin, int end) {
		if (end > begin) {
			int low = begin;
			int high = end;
			byte middle = arr[(begin + end) / 2];
			while (low <= high) {
				while (low < end && arr[low] < middle) {
					low++;
				}
				while (high > begin && arr[high] > middle) {
					high--;
				}
				if (low <= high) {
					byte temp = arr[low];
					arr[low] = arr[high];
					arr[high] = temp;
					low++;
					high--;
				}
			}
			if (begin < high) {
				sort(arr, begin, high);
			}
			if (low < end) {
				sort(arr, low, end);
			}
		}
	}// sort (byte[] arr, int begin, int end)

	/**
	 * sort an Object array
	 * 
	 * @param arr
	 *            [] Onject
	 * @param c
	 *            CustomObjectComparator
	 */
	public void sort(Object[] arr, CustomObjectComparator c) {
		sort(arr, 0, arr.length - 1, c);
	}// sort (Object[] arr, CustomObjectComparator c)

	/**
	 * sort an Object array
	 * 
	 * @param arr
	 *            [] Onject
	 * @param begin
	 *            int
	 * @param end
	 *            int
	 * @param c
	 *            CustomObjectComparator
	 */
	public void sort(Object[] arr, int begin, int end, CustomObjectComparator c) {
		if (end > begin) {
			int low = begin;
			int high = end;
			Object middle = arr[(begin + end) / 2];
			while (low <= high) {
				while (low < end && c.compare(arr[low], middle) < 0) {
					low++;
				}
				while (high > begin && c.compare(arr[high], middle) > 0) {
					high--;
				}
				if (low <= high) {
					Object temp = arr[low];
					arr[low] = arr[high];
					arr[high] = temp;
					low++;
					high--;
				}
			}
			if (begin < high) {
				sort(arr, begin, high, c);
			}
			if (low < end) {
				sort(arr, low, end, c);
			}
		}
	}// sort (Object[] arr, int begin, int end, CustomObjectComparator c)

	/**
	 * sort an List ar
	 * 
	 * @param arr
	 *            [] List
	 * @param c
	 *            CustomObjectComparator
	 */
	public void sort(List arr, CustomObjectComparator c) {
		sort(arr, 0, arr.size() - 1, c);
	}// sort (List arr, CustomObjectComparator c)

	/**
	 * sort an List
	 * 
	 * @param arr
	 *            [] List
	 * @param begin
	 *            int
	 * @param end
	 *            int
	 * @param c
	 *            CustomObjectComparator
	 */
	public void sort(List arr, int begin, int end, CustomObjectComparator c) {
		if (end > begin) {
			int low = begin;
			int high = end;
			Object middle = arr.get((begin + end) / 2);
			while (low <= high) {
				while (low < end && c.compare(arr.get(low), middle) < 0) {
					low++;
				}
				while (high > begin && c.compare(arr.get(high), middle) > 0) {
					high--;
				}
				if (low <= high) {
					Object temp = arr.get(low);
					arr.set(low, arr.get(high));
					arr.set(high, temp);
					low++;
					high--;
				}
			}
			if (begin < high) {
				sort(arr, begin, high, c);
			}
			if (low < end) {
				sort(arr, low, end, c);
			}
		}
	}// sort (List arr, int begin, int end, CustomObjectComparator c)
}// QuickSort